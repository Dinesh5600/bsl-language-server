/*
 * This file is a part of BSL Language Server.
 *
 * Copyright (c) 2018-2023
 * Alexey Sosnoviy <labotamy@gmail.com>, Nikita Fedkin <nixel2007@gmail.com> and contributors
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 *
 * BSL Language Server is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * BSL Language Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with BSL Language Server.
 */
package com.github._1c_syntax.bsl.languageserver.context.symbol.description;

import com.github._1c_syntax.bsl.languageserver.context.symbol.MethodSymbol;
import com.github._1c_syntax.bsl.languageserver.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MethodDescriptionTest {

  private List<MethodDescription> methodsWithDescription;

  @BeforeEach
  void prepare() {
    if (methodsWithDescription == null) {
      var documentContext = TestUtils.getDocumentContextFromFile("./src/test/resources/context/symbol/MethodDescription.bsl");
      var methods = documentContext.getSymbolTree().getMethods();

      assertThat(methods.size()).isEqualTo(14);

      methodsWithDescription = methods.stream()
        .map(MethodSymbol::getDescription)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());

      assertThat(methodsWithDescription.size()).isEqualTo(13);
    }
  }

  @Test
  void testMethod13() {
    var method = methodsWithDescription.get(12);
    assertThat(method.getPurposeDescription()).isEqualTo("Значения реквизитов, прочитанные из информационной базы для нескольких объектов.\n" +
      "\nЕсли необходимо зачитать реквизит независимо от прав текущего пользователя,\n" +
      "то следует использовать предварительный переход в привилегированный режим.");
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).hasSize(3);
    assertThat(method.getReturnedValue()).hasSize(1);
    assertThat(method.getLink()).isEmpty();

    var param = method.getParameters().get(0);
    assertThat(param.getName()).isEqualTo("Ссылки");
    assertThat(param.getTypes()).hasSize(1);
    assertThat(param.getTypes().get(0).getName()).isEqualTo("Массив");
    assertThat(param.getTypes().get(0).getDescription()).isEqualTo("массив ссылок на объекты одного типа.\n" +
      "Значения массива должны быть ссылками на объекты одного типа.\n" +
      "если массив пуст, то результатом будет пустое соответствие.");
    assertThat(param.isHyperlink()).isFalse();

    param = method.getParameters().get(1);
    assertThat(param.getName()).isEqualTo("Реквизиты");
    assertThat(param.getTypes()).hasSize(1);
    assertThat(param.getTypes().get(0).getName()).isEqualTo("Строка");
    assertThat(param.getTypes().get(0).getDescription()).isEqualTo(
      "имена реквизитов перечисленные через запятую, в формате требований к свойствам\n" +
      "структуры. Например, \"Код, Наименование, Родитель\".");
    assertThat(param.isHyperlink()).isFalse();

    param = method.getParameters().get(2);
    assertThat(param.getName()).isEqualTo("ВыбратьРазрешенные");
    assertThat(param.getTypes()).hasSize(1);
    assertThat(param.getTypes().get(0).getName()).isEqualTo("Булево");
    assertThat(param.getTypes().get(0).getDescription()).isEqualTo(
      "если Истина, то запрос к объектам выполняется с учетом прав пользователя, и в случае,\n" +
      "- если какой-либо объект будет исключен из выборки по правам, то этот объект\n" +
      "будет исключен и из результата;\n" +
      "- если Ложь, то возникнет исключение при отсутствии прав на таблицу\n" +
      "или любой из реквизитов.");
    assertThat(param.isHyperlink()).isFalse();

    var type = method.getReturnedValue().get(0);
    assertThat(type.getName()).isEqualTo("Соответствие");
    assertThat(type.getDescription()).isEqualTo("список объектов и значений их реквизитов:");
    assertThat(type.getParameters()).hasSize(2);

  }

  @Test
  void testMethod12() {
    var method = methodsWithDescription.get(11);
    assertThat(method.getPurposeDescription()).isEmpty();
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).isEmpty();
    assertThat(method.getReturnedValue()).hasSize(1);
    assertThat(method.getLink()).isEmpty();

    var type = method.getReturnedValue().get(0);
    assertThat(type.getName()).isEmpty();
    assertThat(type.getDescription()).isEmpty();
    assertThat(type.getParameters()).isEmpty();
    assertThat(type.isHyperlink()).isTrue();
    assertThat(type.getLink()).isEqualTo("ОбщийМодуль.Метод()");
  }

  @Test
  void testMethod11() {
    var method = methodsWithDescription.get(10);
    assertThat(method.getPurposeDescription()).isEmpty();
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).hasSize(1);
    assertThat(method.getReturnedValue()).isEmpty();
    assertThat(method.getLink()).isEmpty();

    var param = method.getParameters().get(0);
    assertThat(param.getName()).isEmpty();
    assertThat(param.getTypes()).isEmpty();
    assertThat(param.isHyperlink()).isTrue();
    assertThat(param.getLink()).isEqualTo("ОбщийМодуль.Метод()");
  }

  @Test
  void testMethod10() {
    var method = methodsWithDescription.get(9);
    assertThat(method.getPurposeDescription())
      .isEqualTo("См. ОбщийМодуль.Метод()");
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).isEmpty();
    assertThat(method.getReturnedValue()).isEmpty();
    assertThat(method.getLink()).isEqualTo("ОбщийМодуль.Метод()");
  }

  @Test
  void testMethod9() {
    var method = methodsWithDescription.get(8);
    assertThat(method.getPurposeDescription())
      .isEqualTo("Описание метода\nНесколько строк");
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).isEmpty();
    assertThat(method.getReturnedValue()).hasSize(1);
    assertThat(method.getLink()).isEmpty();
  }

  @Test
  void testMethod8() {
    var method = methodsWithDescription.get(7);
    assertThat(methodsWithDescription.get(7).getPurposeDescription())
      .isEqualTo("Описание метода\nНесколько строк");
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).hasSize(3);
    assertThat(method.getReturnedValue()).hasSize(1);
    assertThat(method.getLink()).isEmpty();
  }

  @Test
  void testMethod7() {
    var method = methodsWithDescription.get(6);
    assertThat(method.getPurposeDescription())
      .isEqualTo("Описание метода\nНесколько строк");
    assertThat(method.isDeprecated()).isTrue();
    assertThat(method.getDeprecationInfo())
      .isEqualTo("Следует использовать новую см. ОбщегоНазначения.Метод()");
    assertThat(method.getExamples()).hasSize(2);
    assertThat(method.getExamples().get(0))
      .isEqualTo("Пример0(Тип11, Тип21) - описание ...");
    assertThat(method.getCallOptions()).hasSize(2);
    assertThat(method.getCallOptions().get(0))
      .isEqualTo("УниверсальнаяПроцедура(Тип11, Тип21) - описание ...");
    assertThat(method.getParameters()).hasSize(3);
    assertThat(method.getReturnedValue()).hasSize(2);
    assertThat(method.getLink()).isEmpty();
  }

  @Test
  void testMethod6() {
    var method = methodsWithDescription.get(5);
    assertThat(method.getPurposeDescription()).isEmpty();
    assertThat(method.isDeprecated()).isTrue();
    assertThat(method.getDeprecationInfo())
      .isEqualTo("Следует использовать новую см. ОбщегоНазначения.Метод()");
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).isEmpty();
    assertThat(method.getReturnedValue()).isEmpty();
    assertThat(method.getLink()).isEmpty();
  }

  @Test
  void testMethod5() {
    var method = methodsWithDescription.get(4);
    assertThat(method.getPurposeDescription()).isEmpty();
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).hasSize(2);
    assertThat(method.getExamples().get(0))
      .isEqualTo("УниверсальнаяПроцедура(Тип11, Тип21) - описание ...");
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).isEmpty();
    assertThat(method.getReturnedValue()).isEmpty();
    assertThat(method.getLink()).isEmpty();
  }

  @Test
  void testMethod4() {
    var method = methodsWithDescription.get(3);
    assertThat(method.getPurposeDescription()).isEmpty();
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).isEmpty();
    assertThat(method.getReturnedValue()).hasSize(2);
    assertThat(method.getLink()).isEmpty();
    var type = method.getReturnedValue().get(0);
    assertThat(type.getDescription()).isEqualTo("ссылка на предопределенный элемент.");
    assertThat(type.getName()).isEqualTo("ЛюбаяСсылка");
    assertThat(type.getParameters()).isEmpty();
    type = method.getReturnedValue().get(1);
    assertThat(type.getDescription()).isEqualTo("если предопределенный элемент есть в метаданных, но не создан в ИБ.");
    assertThat(type.getName()).isEqualTo("Неопределено");
    assertThat(type.getParameters()).isEmpty();
  }

  @Test
  void testMethod3() {
    var method = methodsWithDescription.get(2);
    assertThat(method.getPurposeDescription()).isEmpty();
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).hasSize(3);
    assertThat(method.getCallOptions().get(0))
      .isEqualTo("УниверсальнаяПроцедура(Тип11, Тип21) - описание ...");
    assertThat(method.getParameters()).isEmpty();
    assertThat(method.getReturnedValue()).isEmpty();
    assertThat(method.getLink()).isEmpty();
  }

  @Test
  void testMethod2() {

    var method = methodsWithDescription.get(1);


    assertThat(method.getPurposeDescription()).isEmpty();
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).hasSize(8);
    assertThat(method.getLink()).isEmpty();


    assertParameterWithoutTypeAndDescription(method.getParameters().get(0), "ПараметрБезТипаИОписания");


    assertParameterWithTypeAndEmptyDescription(method.getParameters().get(1), "ПараметрСТипом", "Произвольный");


    assertParameterWithTypeAndLinkDescription(method.getParameters().get(2), "ПараметрСОписаниемСсылкой", "см. ПодключаемыеКомандыПереопределяемый.ПриОпределенииКомандПодключенныхКОбъекту.НастройкиФормы");


  }

  private void assertParameterWithoutTypeAndDescription(Parameter parameter, String expectedName) {
    assertThat(parameter.getName()).isEqualTo(expectedName);
    assertThat(parameter.getTypes()).isEmpty();
  }

  private void assertParameterWithTypeAndEmptyDescription(Parameter parameter, String expectedName, String expectedType) {
    assertThat(parameter.getName()).isEqualTo(expectedName);
    assertThat(parameter.getTypes()).hasSize(1);
    TypeDescription type = parameter.getTypes().get(0);
    assertThat(type.getDescription()).isEmpty();
    assertThat(type.getName()).isEqualTo(expectedType);
    assertThat(type.getParameters()).isEmpty();
  }

  private void assertParameterWithTypeAndLinkDescription(Parameter parameter, String expectedName, String expectedLinkDescription) {
    assertThat(parameter.getName()).isEqualTo(expectedName);
    assertThat(parameter.getTypes()).hasSize(1);
    TypeDescription type = parameter.getTypes().get(0);
    assertThat(type.getDescription()).isEmpty();
    assertThat(type.getName()).isEqualTo(expectedLinkDescription);
    assertThat(type.getParameters()).isEmpty();
  }

  @Test
  void testMethod1() {
    var method = methodsWithDescription.get(0);
    assertThat(method.getPurposeDescription())
      .isNotEmpty()
      .isEqualTo("Описание метода");
    assertThat(method.isDeprecated()).isFalse();
    assertThat(method.getDeprecationInfo()).isEmpty();
    assertThat(method.getExamples()).isEmpty();
    assertThat(method.getCallOptions()).isEmpty();
    assertThat(method.getParameters()).isEmpty();
    assertThat(method.getReturnedValue()).isEmpty();
    assertThat(method.getLink()).isEmpty();
  }
}