
(ns app.comp.missing
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

(def style-missing {:padding "8px 16px"})

(def style-huge {:font-size 40, :font-weight 100, :font-family "Josefin Sans"})

(defcomp
 comp-missing
 (router)
 (div
  {:style style-missing}
  (div {} (<> span "Missing page!" style-huge))
  (div {} (<> span router nil))))
