
(ns app.comp.header
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(def style-header
  (merge
   ui/row
   {:font-size 16,
    :border-bottom (str "1px solid " (hsl 0 0 90)),
    :padding "0 16px",
    :font-family "Josefin Sans",
    :font-weight 100,
    :justify-content :space-between,
    :cursor :pointer}))

(def style-entry {})

(defn on-navigate [entry-id]
  (fn [e d! m!] (d! :router/navigate {:name entry-id, :data nil})))

(defn render-entry [entry-name entry-id]
  (<> span entry-name style-entry)
  (span {:inner-text entry-name, :style style-entry, :on {:click (on-navigate entry-id)}}))

(defcomp
 comp-header
 ()
 (div
  {:style style-header}
  (div
   {}
   (render-entry "Tasks" :task-list)
   (=< 32 0)
   (render-entry "Tags" :tag-list)
   (=< 32 0)
   (render-entry "Stats" :stats)
   (=< 32 0))
  (div {} (render-entry "Profile" :progile))))
