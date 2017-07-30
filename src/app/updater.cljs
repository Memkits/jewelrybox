
(ns app.updater (:require [app.updater.router :as router] [app.updater.tag :as tag]))

(defn updater [store op op-data op-id]
  (println "calling updater" op op-data)
  (case op
    :router/navigate (router/navigate store op-data op-id)
    :tag/edit (tag/edit store op-data op-id)
    (do (println "Unhandled action:" op) store)))
