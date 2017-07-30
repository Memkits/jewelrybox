
(ns app.comp.tag-editor
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(defcomp comp-tag-editor () (div {} (<> span "Tag editor" nil)))
