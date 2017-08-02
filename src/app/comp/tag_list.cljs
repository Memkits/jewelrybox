
(ns app.comp.tag-list
  (:require-macros [respo.macros :refer [defcomp <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.tag-item :refer [comp-tag-item]]
            [app.style :as style]))

(def style-body (merge))

(def style-list (merge ui/flex {}))

(def style-control {:font-family "Josefin Sans", :cursor :pointer})

(def style-actions (merge ui/flex {}))

(defn on-create [e d! m!] (d! :router/navigate {:name :tag-editor, :data nil}))

(defn on-input [e d! m!] (m! (:value e)))

(defn on-keydown [state]
  (fn [e d! m!]
    (if (and (not (string/blank? state)) (= 13 (:key-code e)))
      (do (d! :tag/quick-create state) (m! "")))))

(defcomp
 comp-tag-list
 (states tags)
 (let [state (or (:data states) "")]
   (div
    {:style style-body}
    (div
     {:style style-actions}
     (input
      {:value state,
       :placeholder "Quick tag",
       :style style/input,
       :on {:input on-input, :keydown (on-keydown state)}})
     (=< 8 nil)
     (span {:inner-text "Create tag", :style style-control, :on {:click on-create}}))
    (=< nil 16)
    (div
     {:style style-list}
     (->> tags
          (sort-by (fn [entry] (- (:created-time (val entry)))))
          (map (fn [entry] (let [k (key entry), tag (val entry)] [k (comp-tag-item tag)]))))))))
