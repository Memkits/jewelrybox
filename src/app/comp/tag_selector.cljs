
(ns app.comp.tag-selector
  (:require-macros [respo.macros :refer [defcomp <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.style :as style]))

(defn on-input [state] (fn [e d! m!] (m! (assoc state :query (:value e)))))

(def style-input (merge ui/input {:width 120, :background-color :transparent}))

(def style-tag
  {:margin-right 8,
   :padding "0 8px",
   :background-color (hsl 240 70 86),
   :border-radius "4px",
   :color :white,
   :line-height "32px"})

(def style-line {:display :inline-block})

(def inital-state {:query "", :searching? false})

(defn on-focus [state] (fn [e d! m!] (m! (assoc state :searching? true))))

(defn on-blur [state] (fn [e d! m!] (m! (assoc state :searching? false))))

(defcomp
 comp-tag-selector
 (states tags)
 (let [state (or (:data states) inital-state)]
   (div
    {}
    (input
     {:value (:query state),
      :placeholder "Filter tags",
      :style style-input,
      :on {:input (on-input state), :focus (on-focus state), :blur (on-blur state)}})
    (=< 8 nil)
    (if (:searching? state)
      (div
       {:style style-line}
       (->> tags
            (filter
             (fn [entry]
               (let [text (-> entry val :name string/lower-case)
                     chunk (-> state :query string/lower-case)]
                 (string/includes? text chunk))))
            (sort-by (fn [entry] (-> entry val :name count)))
            (take 5)
            (map
             (fn [entry]
               [(key entry) (span {:inner-text (:name (val entry)), :style style-tag})]))))))))
