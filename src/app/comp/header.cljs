
(ns app.comp.header
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(defn on-navigate [entry-id]
  (fn [e d! m!] (d! :router/navigate {:name entry-id, :data nil})))

(def style-entry {:display :inline-block, :line-height "40px", :padding "0 8px"})

(defn render-entry [entry-name entry-id]
  (<> span entry-name style-entry)
  (span
   {:class-name "nav-entry",
    :inner-text entry-name,
    :style style-entry,
    :on {:click (on-navigate entry-id)}}))

(def style-header
  (merge
   ui/row
   {:font-size 16,
    :border-bottom (str "1px solid " (hsl 0 0 90)),
    :padding "0 16px",
    :font-family "Josefin Sans",
    :justify-content :space-between,
    :cursor :pointer}))

(defcomp
 comp-header
 ()
 (div
  {:style style-header}
  (div
   {}
   (render-entry "Tasks" :task-list)
   (=< 20 0)
   (render-entry "Tags" :tag-list)
   (=< 20 0)
   (render-entry "Stats" :stats)
   (=< 20 0))
  (div {} (render-entry "Profile" :progile))))
