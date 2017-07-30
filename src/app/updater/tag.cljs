
(ns app.updater.tag (:require [app.schema :as schema]))

(defn edit [store op-data op-id]
  (let [data-id (:id op-data)]
    (if (some? data-id)
      (update-in store [:tags data-id] (fn [x] (merge x op-data)))
      (assoc-in store [:tags op-id] (merge schema/tag op-data {:id op-id})))))
