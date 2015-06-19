(ns scratch.core
  (require [clojure.string :as str :only (split-lines join split)]))

(defn numberify [str]
  (vec (map read-string (str/split str #" "))))

(defn take-while+1 [pred coll]
    (lazy-seq (when-let [[x & xs] (seq coll)]
                (if (pred x)
                  (cons x (take-while+1 pred xs)) (list x)))))

(defn trade [acc bought wrappers]
  (let [traded (int (Math/floor (/ bought wrappers)))
        remainder (if (= 0 traded)
                    0
                    (- bought (* traded wrappers)))
        result (+ traded remainder)
        ]
    ;; (prn "===============")
    ;; (prn (str "acc = " acc))
    ;; (prn (str "bought = " bought))
    ;; (prn (str "wrappers = " wrappers))
    ;; (prn (str "traded = ") traded)
    ;; (prn (str "remainder = " remainder))
    ;; (prn (str "result = " result))
    ;; (prn (str "cunt = " (< result wrappers)))
    ;; (prn "===============")
    (if (< result wrappers)
      (+ acc traded)
      (trade (+ acc traded) result wrappers)
      )
    )
  )

; work out how many chocs are bought by dividing cash by amount (/ 6 2)
; exchange wrappers by dividing bought by wrappers (/ 3 2)
; store remaining
; exchange wrappers + remaining for new wrappers
; repeat until you can't exchange any more wrappers
(defn process [[cash amount wrappers]]
  (let [bought (int (Math/floor (/ cash amount)))
        remaining (rem cash amount)]
    (trade bought bought wrappers)))

(defn print-result [[x & xs]]
  (prn x)
  (if (seq xs)
    (recur xs)))

(let [input "1\n77984 530 67091"
      lines (str/split-lines input)
      length (read-string (first lines))
      inputs (map numberify (rest lines))]
  (prn (map process inputs)))
; "1\n43203 60 5" => 899
