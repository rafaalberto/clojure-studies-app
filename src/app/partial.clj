(ns app.partial)

(defn print-person [name last-name]
  (str "You are " name " " last-name "!"))

(println (print-person "Rafael" "Alberto"))

(def build-name (partial print-person "Rafael"))
(println (build-name "Alberto"))