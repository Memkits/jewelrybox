
(ns app.comp.task-item
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
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

(defn on-click [task]
  (fn [e d! m!] (d! :router/navigate {:name :task-detail, :data (:id task)})))

(defcomp
 comp-task-item
 (task)
 (let [text (:text task), detail (:detail task)]
   (div
    {:style style-task, :on {:click (on-click task)}}
    (div {} (if (string/blank? text) (comp-empty) (<> span text nil)))
    (div {} (if (string/blank? detail) (comp-empty) (<> span detail nil))))))
