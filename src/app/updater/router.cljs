
(ns app.updater.router )

(defn navigate [store op-data op-id] (assoc store :router op-data))
