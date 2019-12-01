
(ns app.style (:require [respo-ui.style :as ui]))

(def button (merge ui/button {}))

(def input (merge ui/input {:width 400}))

(def textarea (merge ui/textarea {:width 400}))
