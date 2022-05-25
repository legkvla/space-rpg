(ns elite-rpg.core
    (:require
      [elite-rpg.resources :as r]
      [elite-rpg.sprites :as sprites]))

(enable-console-print!)

(println "Game loading...")

(defn fullscreen-container []
  (let [container (js/PIXI.Container.)]
    (set! (.. container -width) 800)
    (set! (.. container -height) 600)
    container))

(defn init-game-engine []
  (println "Starting Elite RPG Game")
  (let [pixi-dom (.querySelector js/document "#pixi")
        app (js/PIXI.Application.
              #js{:width 800
                  :height 600
                  :backgroundColor 0x000000})
        ; container (js/PIXI.Container.)
        text (js/PIXI.Text. "Elite RPG Game" #js{:fontFamily "Arial" :fontSize 50 :fill "red"})
        a:ship (atom nil)
        a:pos (atom 250)]
    ; (if-let [child (.. pixi-dom -lastElementChild)]
    ;   (.removeChild pixi-dom child))
    (.appendChild pixi-dom (.. app -view))
    (r/load-resources
      (fn [l]
        (reset! a:ship
          (sprites/draw-sample app))))

    (.. text -position (set 250 50))
    (.. app -stage (addChild text))

    (.. app -ticker
      (add
        (fn [delta]
          (when-let [ship @a:ship]
            (swap! a:pos inc)
            (set! (.-position.x ship) @a:pos)
            (.. text -position (set 250 50))))))

    (.start app)))


(set! (.-onload js/window) init-game-engine)


(defn on-js-reload [])
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
