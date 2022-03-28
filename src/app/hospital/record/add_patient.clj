(ns app.hospital.record.add-patient
  (:use [clojure pprint]))

(defn add-patient [patients patient]
  (if-let [id (:id patient)]
    (assoc patients id patient)
    (throw (ex-info "Patient does not have id" {:patient patient}))))

(defn test-add-patient []
  (let [patients {}
        rafael {:id 1 :username "rafa" :birthdate "17/03/1989"}
        john {:id 2 :username "john" :birthdate "29/04/1956"}
        mary {:username "mary" :birthdate "12/12/2000"}]
    (pprint (add-patient patients rafael))
    (pprint (add-patient patients john))
    (pprint (add-patient patients mary))))

(test-add-patient)