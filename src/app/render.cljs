
(ns app.render
  (:require [respo.render.html :refer [make-string]]
            [shell-page.core :refer [make-page spit slurp]]
            [app.comp.container :refer [comp-container]]
            [app.schema :as schema]))

(def base-info
  {:title "Jewelrybox", :icon "http://logo.mvc-works.org/mvc.png", :ssr nil, :inner-html nil})

(defn dev-page []
  (make-page
   ""
   (merge
    base-info
    {:styles [], :scripts ["/main.js" "/browser/lib.js" "/browser/main.js"]})))

(defn prod-page []
  (let [html-content (make-string (comp-container schema/store))
        manifest (.parse js/JSON (slurp "dist/assets-manifest.json"))
        cljs-manifest (.parse js/JSON (slurp "dist/manifest.json"))]
    (make-page
     html-content
     (merge
      base-info
      {:styles [(aget manifest "main.css")],
       :scripts [(aget manifest "main.js")
                 (-> cljs-manifest (aget 0) (aget "js-name"))
                 (-> cljs-manifest (aget 1) (aget "js-name"))],
       :ssr "respo-ssr"}))))

(defn main! []
  (if (= js/process.env.env "dev")
    (spit "target/index.html" (dev-page))
    (spit "dist/index.html" (prod-page))))
