(ns scratch.tree)

(def t [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
        [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])

((fn [t]
   (prn (nth t 1))
   (prn (nth t 2))
   )t)




         ;;         [1
         ;;     [2
         ;;   nil [3
         ;;     [4
         ;; [5 nil nil] [6 nil nil]] nil ]]

         ;;     [2
         ;;   [3
         ;; nil [4
         ;;   [6 nil nil] [5 nil nil] ] ] nil] ]
