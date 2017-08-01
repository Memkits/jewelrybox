
(ns app.comp.stats
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(def style-body (merge ui/flex {:padding "8px 16px"}))

(defcomp
 comp-stats
 (store)
 (div
  {:style style-body}
  (div {} (<> span (str "Tasks: " (count (:tasks store))) nil))
  (div {} (<> span (str "Tags: " (count (:tags store))) nil))))
