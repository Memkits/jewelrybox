
(ns app.comp.tag-list
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(def style-body (merge ui/row ui/flex {:padding "8px 16px"}))

(def style-list (merge ui/flex {}))

(def style-control {:font-family "Josefin Sans", :cursor :pointer})

(def style-actions (merge ui/flex {}))

(defn on-create [e d! m!] (d! :router/navigate {:name :tag-editor, :data nil}))

(defcomp
 comp-tag-list
 (tags)
 (div
  {:style style-body}
  (div {:style style-list} (<> span tags nil))
  (div
   {:style style-actions}
   (span {:inner-text "Create tag", :style style-control, :on {:click on-create}}))))
