
(ns app.comp.tag-item
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(def style-tag
  {:border (str "1px solid " (hsl 0 0 100 0.5)),
   :padding "8px 16px",
   :margin-bottom 15,
   :cursor :pointer})

(defn on-click [tag]
  (fn [e d! m!] (d! :router/navigate {:name :tag-item, :data (:id tag)})))

(defcomp
 comp-tag-item
 (tag)
 (div
  {:style style-tag, :on {:click (on-click tag)}}
  (div {} (<> span (:name tag) nil))
  (div {} (<> span (:detail tag) nil))))
