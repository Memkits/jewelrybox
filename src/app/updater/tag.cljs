
(ns app.updater.tag (:require [app.schema :as schema]))

(defn edit [store op-data op-id op-time]
  (let [data-id (:id op-data)]
    (if (some? data-id)
      (update-in store [:tags data-id] (fn [x] (merge x op-data)))
      (assoc-in
       store
       [:tags op-id]
       (merge schema/tag op-data {:id op-id, :created-time op-time})))))

(defn quick-create [store op-data op-id op-time]
  (assoc-in
   store
   [:tags op-id]
   (merge schema/tag {:name op-data, :created-time op-time, :id op-id})))
