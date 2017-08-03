
(ns app.comp.task-list
  (:require-macros [respo.macros :refer [defcomp cursor-> <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.task-item :refer [comp-task-item]]
            [app.style :as style]))

(defn on-input [e d! m!] (m! (:value e)))

(def style-advanced {:font-size 14, :cursor :pointer, :color (hsl 0 0 0 0.4)})

(def style-body (merge))

(def style-list (merge ui/flex {}))

(def style-actions (merge ui/row-center {:padding-bottom 16, :justify-content :start}))

(defn on-keydown [state]
  (fn [e d! m!]
    (let [key-code (:key-code e)]
      (if (and (= 13 key-code) (not (string/blank? state)))
        (do (d! :task/quick-create state) (m! ""))))))

(defn on-create [e d! m!] (d! :router/navigate {:name :task-editor, :data nil}))

(defcomp
 comp-task-list
 (states tasks tags)
 (let [state (or (:data states) "")]
   (div
    {:style style-body}
    (div
     {:style style-actions}
     (input
      {:style style/input,
       :placeholder "Quick task",
       :value state,
       :on {:input on-input, :keydown (on-keydown state)}})
     (=< 8 nil)
     (span {:inner-text "Advanced", :style style-advanced, :on {:click on-create}}))
    (div
     {:style style-list}
     (->> tasks
          (sort-by (fn [entry] (let [[k task] entry] (- (:created-time task)))))
          (map
           (fn [entry]
             (let [[k task] entry] [k (cursor-> k comp-task-item states task tags)]))))))))
