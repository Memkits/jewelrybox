
(ns app.comp.tag-item
  (:require-macros [respo.macros :refer [defcomp <> div button input textarea span]])
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [respo.comp.inspect :refer [comp-inspect]]))

(def style-tag
  {:border (str "1px solid " (hsl 0 0 90)), :padding 8, :margin-bottom 15, :cursor :pointer})

(def style-text (merge ui/input {:min-width 400, :background-color :transparent}))

(def style-detail
  (merge ui/input {:min-width 400, :background-color :transparent, :color (hsl 0 0 60)}))

(defn on-edit [task-id k] (fn [e d! m!] (d! :tag/edit (assoc {:id task-id} k (:value e)))))

(defcomp
 comp-tag-item
 (tag)
 (div
  {:style style-tag}
  (div
   {}
   (input
    {:value (:name tag),
     :placeholder "Tag name",
     :style style-text,
     :on {:input (on-edit (:id tag) :name)}}))
  (div
   {}
   (input
    {:value (:detail tag),
     :placeholder "Tag detail",
     :style style-detail,
     :on {:input (on-edit (:id tag) :detail)}}))))

(defn on-click [tag]
  (fn [e d! m!] (d! :router/navigate {:name :tag-detail, :data (:id tag)})))
