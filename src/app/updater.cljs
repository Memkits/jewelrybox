
(ns app.updater
  (:require [app.updater.router :as router]
            [app.updater.task :as task]
            [app.updater.tag :as tag]))

(defn updater [store op op-data op-id op-time]
  (println op op-data)
  (case op
    :router/navigate (router/navigate store op-data op-id)
    :tag/edit (tag/edit store op-data op-id op-time)
    :tag/quick-create (tag/quick-create store op-data op-id op-time)
    :task/edit (task/edit store op-data op-id op-time)
    :task/quick-create (task/quick-create store op-data op-id op-time)
    :task/remove-task (task/remove-task store op-data op-id op-time)
    (do (println "Unhandled action:" op) store)))
