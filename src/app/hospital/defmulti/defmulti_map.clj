(ns app.hospital.defmulti.defmulti-map
  (:use [clojure pprint]))

(defn authorization-type [order]
  (let [patient (:patient order)
        status (:status patient)]
    (cond (= :emergency status) :authorized
          (contains? patient :insurances) :insurance
          :else :minimum-credit)))

(defmulti should-pre-authorize? authorization-type)

(defmethod should-pre-authorize? :authorized [_]
  false)

(defmethod should-pre-authorize? :insurance [order]
  (not (some #(= % (:service order)) (:insurances (:patient order)))))

(defmethod should-pre-authorize? :minimum-credit [order]
  (>= (:value order 0) 50))

(let [particular {:id 1 :name "John" :status :ok}
      insurance {:id 2 :name "Mary" :status :ok :insurances [:blood-test]}]
  (pprint (should-pre-authorize? {:patient particular :value 30 :service :blood-test}))
  (pprint (should-pre-authorize? {:patient insurance :value 100 :service :x-ray})))