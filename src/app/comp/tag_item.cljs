
(ns app.comp.tag-item
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [respo.comp.inspect :refer [comp-inspect]]
            [app.comp.empty :refer [comp-empty]]))

(def style-tag
  {:border (str "1px solid " (hsl 0 0 90)),
   :padding "8px 16px",
   :margin-bottom 15,
   :cursor :pointer})

(defn on-click [tag]
  (fn [e d! m!] (d! :router/navigate {:name :tag-detail, :data (:id tag)})))

(defcomp
 comp-tag-item
 (tag)
 (let [text (:name tag), detail (:detail tag)]
   (div
    {:style style-tag, :on {:click (on-click tag)}}
    (div {} (if (string/blank? text) (comp-empty) (<> span text nil)))
    (div {} (if (string/blank? detail) (comp-empty) (<> span (:detail tag) nil))))))
