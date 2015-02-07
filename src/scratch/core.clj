(ns scratch.core
  (require [clojure.string :as str :only (split-lines join)]))

(defn is-prime? [n]
  (or (= 2 n)
   (not-any? #(= 0 (mod n %)) (cons 2 (range 3 (inc (Math/sqrt n)) 2)))))

(defn nth-prime [n]
  (last (take n (filter #(is-prime? %) (cons 2 (iterate (partial + 2) 3))))))

(defn print-primes [[x & xs]]
  (prn x)
  (if (seq xs)
    (recur xs)))


(let [input "2\n3\n6"
      ranks (rest (map read-string (str/split-lines input)))
      primes (map nth-prime ranks)]
  (print-primes primes))
;(work (slurp *in*))
