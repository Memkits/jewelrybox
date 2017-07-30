
(ns app.updater.router )

(defn navigate [store op-data] (assoc store :router op-data))
