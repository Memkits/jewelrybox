
(ns app.updater )

(defn updater [store op op-data] (case op :inc (update store :data inc) store))
