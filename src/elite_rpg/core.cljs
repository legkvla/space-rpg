(ns elite-rpg.core
    (:require
      [elite-rpg.resources :as r]
      [elite-rpg.scene :as scene]))

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
        text (js/PIXI.Text. "Elite RPG Game" #js{:fontFamily "Arial" :fontSize 50 :fill "red"})]

    (.appendChild pixi-dom (.. app -view))
    (r/load-resources #(scene/build-scene app))

    (.. text -position (set 250 50))
    (.. app -stage (addChild text))

    (.. app -ticker
      (add scene/on-tick))

    (.start app)))


(set! (.-onload js/window) init-game-engine)


(defn on-js-reload [])
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
