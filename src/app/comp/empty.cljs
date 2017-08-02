
(ns app.comp.empty
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(def style-empty
  {:font-family "Josefin Sans", :font-weight 100, :font-size 14, :color (hsl 0 0 0 0.8)})

(defcomp comp-empty () (div {:style style-empty} (<> span "Empty" nil)))
