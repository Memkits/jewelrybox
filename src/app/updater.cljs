
(ns app.updater (:require [app.updater.router :as router]))

(defn updater [store op op-data]
  (println "calling updater" op op-data)
  (case op
    :router/navigate (router/navigate store op-data)
    (do (println "Unhandled action:" op) store)))
