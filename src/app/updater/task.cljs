
(ns app.updater.task (:require [app.schema :as schema]))

(defn edit [store op-data op-id op-time]
  (let [data-id (:id op-data)]
    (update
     store
     :tasks
     (fn [tasks]
       (if (some? data-id)
         (update tasks data-id (fn [t] (merge t op-data {:updated-time op-time})))
         (assoc tasks op-id (merge schema/task op-data {:id op-id, :created-time op-time})))))))

(defn quick-create [store op-data op-id op-time]
  (assoc-in
   store
   [:tasks op-id]
   (merge
    schema/task
    {:id op-id, :text op-data, :created-time op-time, :updated-time op-time})))
