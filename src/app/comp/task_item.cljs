
(ns app.comp.task-item
  (:require-macros [respo.macros :refer [defcomp cursor-> <> div button span input textarea]])
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.empty :refer [comp-empty]]
            [app.comp.tag-selector :refer [comp-tag-selector]]))

(def style-task
  {:border (str "1px solid " (hsl 0 0 90)),
   :margin-bottom 16,
   :padding 8,
   :position :relative})

(def style-toolbar {:position :absolute, :top 6, :right 6})

(defn on-navigate [task-id]
  (fn [e d! m!] (d! :router/navigate {:name :task-detail, :data task-id})))

(def style-detail
  (merge ui/input {:min-width 400, :background-color :transparent, :color (hsl 0 0 60)}))

(def style-link {:font-size 12, :color (hsl 240 80 80), :cursor :pointer})

(def style-text (merge ui/input {:min-width 400, :background-color :transparent}))

(defn on-edit [task-id k] (fn [e d! m!] (d! :task/edit (assoc {:id task-id} k (:value e)))))

(defn on-remove [task-id] (fn [e d! m!] (d! :task/remove-task task-id)))

(defcomp
 comp-task-item
 (states task tags)
 (let [text (:text task), detail (:detail task), task-id (:id task)]
   (div
    {:class-name "task-item", :style style-task}
    (div
     {}
     (input
      {:value (:text task),
       :placeholder "Task text",
       :style style-text,
       :on {:input (on-edit (:id task) :text)}}))
    (cursor-> :selector comp-tag-selector states tags)
    (div
     {}
     (input
      {:value (:detail task),
       :placeholder "Task detail",
       :style style-detail,
       :on {:input (on-edit (:id task) :detail)}}))
    (div
     {:style style-toolbar, :class-name "task-item-controls"}
     (span
      {:inner-text "Remove",
       :class-name "task-control",
       :style style-link,
       :on {:click (on-remove task-id)}})
     (=< 8 nil)
     (span
      {:inner-text "Edit",
       :class-name "task-control",
       :style style-link,
       :on {:click (on-navigate task-id)}})))))

(defn on-click [task]
  (fn [e d! m!] (d! :router/navigate {:name :task-detail, :data (:id task)})))
