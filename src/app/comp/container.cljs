
(ns app.comp.container
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.header :refer [comp-header]]
            [app.comp.missing :refer [comp-missing]]
            [app.comp.task-list :refer [comp-task-list]]
            [app.comp.task-detail :refer [comp-task-detail]]
            [app.comp.tag-list :refer [comp-tag-list]]
            [app.comp.tag-detail :refer [comp-tag-detail]]))

(defcomp
 comp-container
 (store)
 (let [router (:router store)]
   (div
    {:style (merge ui/global ui/column)}
    (comp-header)
    (case (:name router)
      :task-list (comp-task-list)
      :task-detail (comp-task-detail)
      :tag-list (comp-tag-list)
      :tag-detail (comp-tag-detail)
      (comp-missing router)))))
