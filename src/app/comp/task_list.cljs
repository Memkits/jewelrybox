
(ns app.comp.task-list
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.task-item :refer [comp-task-item]]))

(def style-body (merge ui/flex ui/row {:padding "8px 16px"}))

(def style-list (merge ui/flex {}))

(def style-actions (merge ui/flex {}))

(def style-control {:font-family "Josefin Sans", :cursor :pointer})

(defn on-create [e d! m!] (d! :router/navigate {:name :task-editor, :data nil}))

(defcomp
 comp-task-list
 (tasks)
 (div
  {:style style-body}
  (div
   {:style style-list}
   (->> tasks (map (fn [entry] (let [[k task] entry] [k (comp-task-item task)])))))
  (=< 16 nil)
  (div
   {:style style-actions}
   (span {:inner-text "Create task", :style style-control, :on {:click on-create}}))))
