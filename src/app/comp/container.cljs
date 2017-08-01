
(ns app.comp.container
  (:require-macros [respo.macros :refer [defcomp cursor-> <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [respo.comp.inspect :refer [comp-inspect]]
            [app.comp.header :refer [comp-header]]
            [app.comp.missing :refer [comp-missing]]
            [app.comp.task-list :refer [comp-task-list]]
            [app.comp.task-detail :refer [comp-task-detail]]
            [app.comp.task-editor :refer [comp-task-editor]]
            [app.comp.tag-list :refer [comp-tag-list]]
            [app.comp.tag-detail :refer [comp-tag-detail]]
            [app.comp.tag-editor :refer [comp-tag-editor]]
            [app.comp.stats :refer [comp-stats]]))

(def style-app (merge ui/global ui/column ui/fullscreen {:color :white}))

(def style-inspect {:bottom 8})

(defcomp
 comp-container
 (store)
 (let [router (:router store)
       states (:states store)
       tags (:tags store)
       tasks (:tasks store)]
   (div
    {:style style-app}
    (comp-header)
    (case (:name router)
      :task-list (comp-task-list tasks)
      :task-detail (comp-task-detail (get tasks (:data router)))
      :task-editor (cursor-> :task-editor comp-task-editor states)
      :tag-list (comp-tag-list tags)
      :tag-detail (comp-tag-detail (get tags (:data router)))
      :tag-editor (cursor-> :tag-editor comp-tag-editor states)
      :stats (comp-stats store)
      (comp-missing router))
    (comp-inspect "Store" store style-inspect))))
