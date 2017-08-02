
(ns app.comp.tag-detail
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(def style-body (merge))

(defcomp
 comp-tag-detail
 (tag)
 (div
  {:style style-body}
  (div {} (<> span (:name tag) nil))
  (div {} (<> span (:detail tag) nil))))
