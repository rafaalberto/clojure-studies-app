(ns app.multimethod)

(defn person-type [person]
  (:type person))

(defmulti print-message person-type)

(defmethod print-message "PF" [person]
  (println "Individual:" (:name person)))

(defmethod print-message "PJ" [person]
  (println "Enterprise:" (:name person)))

(let [individual {:id 1 :name "Rafael" :type "PF"}
      enterprise {:id 2 :name "Clojure SA" :type "PJ"}]
  (print-message individual)
  (print-message enterprise))