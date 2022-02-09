(ns app.composition)

(defn print-name [name] name)

(defn print-greeting [name]
  (str "Hello " (print-name name) ", Good Morning!"))

(def report (comp print-name print-greeting))

(println (report "Rafa"))