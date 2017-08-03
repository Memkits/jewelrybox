
(ns app.comp.task-item
  (:require-macros [respo.macros :refer [defcomp <> div button span input textarea]])
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.empty :refer [comp-empty]]))

(def style-task
  {:border (str "1px solid " (hsl 0 0 90)),
   :margin-bottom 16,
   :padding "8px 16px",
   :cursor :pointer})

(def style-text (merge ui/input {:min-width 400, :background-color :transparent}))

(def style-detail (merge ui/input {:min-width 400, :background-color :transparent}))

(defn on-edit [task-id k] (fn [e d! m!] (d! :task/edit (assoc {:id task-id} k (:value e)))))

(defcomp
 comp-task-item
 (task)
 (let [text (:text task), detail (:detail task)]
   (div
    {:style style-task}
    (div
     {}
     (input
      {:value (:text task),
       :placeholder "Task text",
       :style style-text,
       :on {:input (on-edit (:id task) :text)}}))
    (=< nil 8)
    (div
     {}
     (input
      {:value (:detail task),
       :placeholder "Task detail",
       :style style-detail,
       :on {:input (on-edit (:id task) :detail)}})))))

(defn on-click [task]
  (fn [e d! m!] (d! :router/navigate {:name :task-detail, :data (:id task)})))
