
(ns app.comp.task-editor
  (:require-macros [respo.macros :refer [defcomp <> div button span input textarea button]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.style :as style]))

(def style-body (merge ui/flex {:padding "8px 16px"}))

(def style-actions (merge ui/row {:justify-content :flex-end, :width 400}))

(def initial-state {:text "", :id nil, :detail "", :tag-ids {}})

(defn on-edit [state] (fn [e d! m!] (println "Edit!" state)))

(defn on-change [k state] (fn [e d! m!] (m! (assoc state k (:value e)))))

(defcomp
 comp-task-editor
 (states)
 (let [state (or (:data states) initial-state)]
   (div
    {:style style-body}
    (div {} (<> span "Create task" nil))
    (div
     {}
     (input
      {:placeholder "Task text",
       :value (:text state),
       :style style/input,
       :on {:input (on-change :text state)}}))
    (div {} (<> span "tags" nil))
    (div
     {}
     (textarea
      {:placeholder "Task detail",
       :value (:detail state),
       :style style/textarea,
       :on {:input (on-change :detail state)}}))
    (=< nil 8)
    (div
     {:style style-actions}
     (button {:inner-text "Create", :style style/button, :on {:click (on-edit state)}})))))
