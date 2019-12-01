
(ns app.main
  (:require [respo.core :refer [render! clear-cache! realize-ssr!]]
            [respo.cursor :refer [mutate]]
            [app.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [app.updater :refer [updater]]
            [app.schema :as schema]))

(defonce *store (atom schema/store))

(defn dispatch! [op op-data]
  (let [now (.valueOf (js/Date.))
        id (str now)
        next-store (if (= op :states)
                     (update @*store :states (mutate op-data))
                     (updater @*store op op-data id now))]
    (reset! *store next-store)))

(def mount-target (.querySelector js/document ".app"))

(defn render-app! [renderer] (renderer mount-target (comp-container @*store) dispatch!))

(def ssr? (some? (js/document.querySelector "meta.respo-ssr")))

(defn main! []
  (if ssr? (render-app! realize-ssr!))
  (render-app! render!)
  (add-watch *store :changes (fn [] (render-app! render!)))
  (println "App started."))

(defn reload! [] (clear-cache!) (render-app! render!) (println "Code updated."))

(set! (.-onload js/window) main!)
