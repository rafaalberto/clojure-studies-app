(ns app.list)

(defn display-list [element]
  (println "Display element: " element))

(def items '(1 "Rafael" 2 "Brazil" 3 "São Paulo State"))

(map display-list items)

(println "first element: " (first items))
(println "last element: " (last items))
(println "position 3: " (nth items 3))

;add item to first position of list
(println "add new item: " (conj items "Developer"))

;immutable - the last item added is not here
(map display-list items)