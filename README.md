# TzRecipesApp

Main Stack : Retrofit,Coroutines,Flow,LiveData,Mvvm <br/>
SOLID,OOP,ClenArchitecture<br/>
Разбиение приложения на data, domain, presentation слои<br/>
Маппинг обЪектов через слои<br/>
В качестве предоставления зависимостей я использовал паттерн "Service locator"<br/>

Основные классы приложения покрыты юнит тестами (Repository,Mappers)<br/>

<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/tests.jpg?alt=media&token=3625c36d-fe91-4e7e-ad22-ca13f1607651"/>

Приложение разбито по фичам

### Feature/RA01_fetch_recipes<br/>

Url: https://test.kode-t.ru/recipes

Получение рецептов с интернета и отображение их в списке (согласно написанному юзер стори можете протестировать данную фичу) <br/>
Отображение всех рецептов (в случае успеха)<br/>

Loading state

<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/progress_state.jpg?alt=media&token=c1d5a7c3-e4bb-443a-ad74-8dc2376d39fd" width="300" height="550"/>

Success state

<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/main_screen.jpg?alt=media&token=a36fa42a-cea6-43bc-96ba-962457999c48" width="300" height="550"/>

Failure state(absent internet connection)

<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/main_error.jpg?alt=media&token=5f5cdf8f-93ea-474a-b7ab-41d69aaeedfc" width="300" height="550"/>
​
38
Отображение "похожих" рецептов (Ошибка)
### Feature/RA02_detail_recipe<br/>

Url: https://test.kode-t.ru/recipes/{idRecipe}<br/>

По нажатии на рецепт открывается детальная информация о нем.<br/>
При этом,асинхронно подгружаются "похожие" рецепты<br/>
<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/deatil_screen.jpg?alt=media&token=b7bb07ae-cd90-4e3f-abc5-2e054fa33d64" width="300" height="550"/>

Отображение "похожих" рецептов (Успех)<br/>

<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/similar.jpg?alt=media&token=445a7a12-39d3-48c0-bab1-900c4aa00fe3" width="300" height="550"/>

Отображение "похожих" рецептов (Ошибка). В данном случае я выключил интернет <br/>

<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/similar_error.jpg?alt=media&token=a1fb06ae-79cd-4e1d-bd4a-3fa75af969e6" width="300" height="550"/>

В этой же фиче была реализована возможность просматривать детали "похожих" рецептов<br/>

<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/similar_screen.jpg?alt=media&token=24ea4bdb-a7e7-47fe-b28d-f0de1b9fc88c" width="300" height="550"/>

Apk приложения можно найти в последнем релизе данного проекта

