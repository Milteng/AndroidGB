## Цель практической работы
Применить принципы чистой архитектуры при разработке реального
Android-приложения.
## Что нужно сделать
Разработайте приложение, которое при нажатии кнопки будет предлагать
пользователю, чем он может заняться.
● Занятия возьмите из www.boredapi.com.
● Приложение должно быть разделено на слои.
● Для связывания компонентов приложения между собой используйте
Dagger или Hilt.
1. Сверстайте экран, аналогичный тому, который показан на скриншоте.
2. Создайте интерфейс UsefulActivity в пакете entity с полями,
соответствующими json из ответа API.
3. Создайте UsefulActivityDto в пакете data, который реализует интерфейс
UsefulActivity.
4. Создайте класс UsefulActivitiesRepository в пакете data с методом
getUsefulActivity, который возвращает UsefulActivity.
5. Реализуйте в UsefulActivityRepository получение данных из API, как вы
это делали в модуле по работе с сетью. Для десериализации данных
используется UsefulActivityDto.
6. Создайте GetUsefulActivityUseCase в пакете domain с методом execute,
возвращающим UsefulActivity.
7. В юзкейсе в конструкторе прокиньте UsefulActivitiesRepository и
получите из него данные в методе execute.
8. В пакете presentation создайте MainViewModel, прокиньте в его
конструктор GetUsefulActivityUseCase, добавьте в View-модель
StateFlow для подписки на них в Fragment/Activity, а также метод
reloadUsefulActivity, который должен получать UsefulActivity из
юзкейса и прокидывать его в StateFlow.
9. Добавьте View-модель в Fragment/Activity (стандартно через by
viewModels, если был выбран Hilt, либо заинжектьте его, как было
показано в этом модуле, если был выбран Dagger), подпишитесь на её
изменения, чтобы показывать в UI текст из поля activity из
UsefulActivity.
10.Добавьте вызов метода View-модели reloadUsefulActivity по нажатии на
кнопку.
11. Добавьте поддержку DI: пометьте нужные классы аннотацией @Inject,
затем, если был выбран Dagger, опишите компонент в пакете di, как это
было показано в этом модуле. Если был выбран Hilt, пометьте все
Fragment/Activity аннотацией @AndroidEntryPoint, View-модель —
аннотацией @HiltViewModel, а также создайте Application-класс,
пометьте его аннотацией @HiltAndroidApp и добавьте его в манифест
