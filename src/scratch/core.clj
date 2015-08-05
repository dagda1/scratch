(ns scratch.core
  (require [clojure.string :as str :only (split-lines join split)]))

(defn numberify [str]
  (vec (map read-string (str/split str #" "))))

(defrecord TreeNode [val left right])

(defn build-tree [[l r] rst node]
  (let [left (if (= - 1 l) nil l)
        right (if (= -1 r) nil r)]
    
    )
  )


; (assoc node :left (make-tree left))


;                  1
;                 / \
;                2   3
;               /    /
;              4    5
;             /    /
;            /    /\
;           6    7  8
;           \      / \
;            9    10 11
;
; x =  [2 3]
; xs = 
(let [input "11\n2 3\n4 -1\n5 -2\n6 -1\n7 8\n-1 9\n-1 -1\n10 11\n-1 -1\n-1 -1\n-1 -1"
      lines (str/split-lines input)
      tl (read-string (first lines))
      tree-lines (map numberify (drop 1 (take (inc tl) lines)))
      [x & xs] tree-lines
      ]
  (build-tree x xs (TreeNode. 1 nil nil))
  )
