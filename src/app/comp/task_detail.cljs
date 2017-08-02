
(ns app.comp.task-detail
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(def style-body (merge))

(defcomp
 comp-task-detail
 (task)
 (div
  {:style style-body}
  (div {} (<> span (:text task) nil))
  (div {} (<> span (:detail task) nil))))
