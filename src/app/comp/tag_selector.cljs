
(ns app.comp.tag-selector
  (:require-macros [respo.macros :refer [defcomp <> div button span input]])
  (:require [hsl.core :refer [hsl]]
            [clojure.string :as string]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.style :as style]))

(defn on-input [state] (fn [e d! m!] (m! (assoc state :query (:value e)))))

(def inital-state {:query "", :searching? false})

(def style-menu
  {:position :absolute, :border (str "1px solid " (hsl 0 0 90)), :background-color :white})

(def style-input (merge ui/input {:width 120, :background-color :transparent}))

(defn on-focus [state] (fn [e d! m!] (m! (assoc state :searching? true))))

(def style-selector {:position :relative})

(defn on-blur [state] (fn [e d! m!] (m! (assoc state :searching? false))))

(def style-tag {:padding "0 8px", :line-height "24px"})

(defcomp
 comp-tag-selector
 (states tags)
 (let [state (or (:data states) inital-state)]
   (div
    {:style style-selector}
    (input
     {:value (:query state),
      :placeholder "Filter tags",
      :style style-input,
      :on {:input (on-input state), :focus (on-focus state), :blur (on-blur state)}})
    (=< 8 nil)
    (if (or true (:searching? state))
      (div
       {:style style-menu}
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
               [(key entry) (div {:inner-text (:name (val entry)), :style style-tag})]))))))))
