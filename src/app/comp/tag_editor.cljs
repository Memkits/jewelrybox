
(ns app.comp.tag-editor
  (:require-macros [respo.macros :refer [defcomp <> div button span input textarea button]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.style :as style]))

(def style-body (merge))

(def style-title {:font-family "Josefin Sans", :font-weight 100, :font-size 40})

(def initial-state {:id nil, :name "", :hidden? false, :detail ""})

(defn on-change [k state] (fn [e d! m!] (m! (assoc state k (:value e)))))

(def style-actions (merge ui/row {:width 400, :justify-content :flex-end}))

(defn on-edit [state]
  (fn [e d! m!]
    (d! :tag/edit state)
    (d! :router/navigate {:name :tag-list})
    (m! initial-state)))

(defcomp
 comp-tag-editor
 (states)
 (let [state (or (:data states) initial-state)]
   (div
    {:style style-body}
    (div {:style style-title} (<> span "Edior a tag" nil))
    (div
     {}
     (input
      {:value (:name state),
       :placeholder "Tag name",
       :style style/input,
       :on {:input (on-change :name state)}}))
    (=< nil 8)
    (div
     {}
     (textarea
      {:value (:detail state),
       :placeholder "Tag detail",
       :style style/textarea,
       :on {:input (on-change :detail state)}}))
    (=< nil 8)
    (div
     {:style style-actions}
     (button {:inner-text "Create", :style style/button, :on {:click (on-edit state)}})))))
