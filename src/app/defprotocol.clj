(ns app.defprotocol
  (:use [clojure pprint]))

(defprotocol Menu
  (paint [this]))

(defrecord WindowsMenu
  [description]
  Menu
  (paint [_]
    (str "Displaying Windows...")))

(defrecord LinuxMenu
  [description]
  Menu
  (paint [_]
    (str "Displaying Linux...")))

(defn display []
  (let [windows (->WindowsMenu "Windows Machine")
        linux (->LinuxMenu "Linux Machine")]
    (pprint (paint windows))
    (pprint (paint linux))))

(display)