
(ns app.updater.task (:require [app.schema :as schema]))

(defn edit [store op-data op-id]
  (let [data-id (:id op-data)]
    (update
     store
     :tasks
     (fn [tasks]
       (if (some? data-id)
         (update tasks data-id (fn [t] (merge t op-data)))
         (assoc tasks op-id (merge schema/task op-data {:id op-id})))))))
