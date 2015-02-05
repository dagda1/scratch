(ns scratch.core
  (require [clojure.string :as str :only (split-lines join)]))

(def t "2\n3\n6")

(defn work [input]
  (let [lines (map read-string (str/split-lines input))]
    (letfn [(is-prime? [n]
              (empty? (filter #(= 0 (mod n  %)) (range 2 n))))
            (parse-primes [[x & xs]]
              (prn (last (take x (filter #(is-prime? %) (iterate inc 2)))))
              (if (seq xs)
                (recur xs)))]
       (parse-primes (rest lines)))))

(work t)
;(work (slurp *in*))
; triangle (take-last (- (count sample) 2) sample)
; (take 3 (iterate #(+ 2 %) 1))

