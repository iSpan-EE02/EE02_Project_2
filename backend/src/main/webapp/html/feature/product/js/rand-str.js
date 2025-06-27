/**
 * 根據特定規則從一個整數產生一個4碼的大寫英文字母代碼。
 * 規則：
 * 1. 如果整數在 0 - 456975 之間，直接轉換。
 * 2. 如果整數大於 456975，則取其前6位數字組成新數，再進行轉換。
 * 如果截取後的新數仍然大於 456975，則直接使用 456975。
 *
 * @param {number} inputInteger - 輸入的整數。
 * @returns {string} - 產生的4碼代碼。
 */
export function generateCodeFromInteger(inputInteger) {
  const CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  const CODE_LENGTH = 4;
  const MAX_COMBINATIONS = Math.pow(CHARSET.length, CODE_LENGTH); // 26^4 = 456976
  const MAX_VALUE = MAX_COMBINATIONS - 1; // 456975

  let numberToConvert;

  // 確保輸入是有效的數字
  if (typeof inputInteger !== "number" || isNaN(inputInteger)) {
    inputInteger = 0;
  }

  // 步驟 1: 判斷整數是否超出範圍
  if (inputInteger >= MAX_COMBINATIONS) {
    // 特殊情況：整數超出最大值
    console.log(`輸入值 ${inputInteger} 超出範圍，啟用特殊規則。`);

    // 取其前幾位 (最大數值是6位數，所以我們也取6位)
    const integerAsString = String(inputInteger);
    const maxDigits = String(MAX_VALUE).length; // 6
    const substring = integerAsString.substring(0, maxDigits);

    // 將截取的字串轉回數字
    let effectiveNumber = parseInt(substring, 10);

    // 如果截取後的數字仍然大於最大值，就直接使用最大值
    if (effectiveNumber > MAX_VALUE) {
      console.log(
        `截斷後的數字 ${effectiveNumber} 仍大於最大值，強制使用 ${MAX_VALUE}。`
      );
      numberToConvert = MAX_VALUE;
    } else {
      console.log(`使用截斷後的數字 ${effectiveNumber} 進行轉換。`);
      numberToConvert = effectiveNumber;
    }
  } else {
    // 正常情況：整數在範圍內
    numberToConvert = Math.floor(Math.abs(inputInteger)); // 取絕對值與整數
  }

  // 步驟 2: 將最終確定的數字轉換為 Base26 (4碼)
  if (numberToConvert === 0) {
    return "AAAA";
  }

  let result = "";
  let currentNumber = numberToConvert;

  // 使用迴圈進行基數轉換 (Base26)
  while (result.length < CODE_LENGTH) {
    const remainder = currentNumber % CHARSET.length;
    result = CHARSET[remainder] + result; // 將新字元加在最前面
    currentNumber = Math.floor(currentNumber / CHARSET.length);
  }

  return result;
}
