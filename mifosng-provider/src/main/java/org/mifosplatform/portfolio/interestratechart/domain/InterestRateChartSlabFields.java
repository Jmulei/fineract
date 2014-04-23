/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.interestratechart.domain;

import static org.mifosplatform.portfolio.interestratechart.InterestRateChartSlabApiConstants.amountRangeFromParamName;
import static org.mifosplatform.portfolio.interestratechart.InterestRateChartSlabApiConstants.amountRangeToParamName;
import static org.mifosplatform.portfolio.interestratechart.InterestRateChartSlabApiConstants.annualInterestRateParamName;
import static org.mifosplatform.portfolio.interestratechart.InterestRateChartSlabApiConstants.interestRateForFemaleParamName;
import static org.mifosplatform.portfolio.interestratechart.InterestRateChartSlabApiConstants.interestRateForChildrenParamName;
import static org.mifosplatform.portfolio.interestratechart.InterestRateChartSlabApiConstants.interestRateForSeniorCitizenParamName;
import static org.mifosplatform.portfolio.interestratechart.InterestRateChartSlabApiConstants.descriptionParamName;
import static org.mifosplatform.portfolio.interestratechart.InterestRateChartSlabApiConstants.fromPeriodParamName;
import static org.mifosplatform.portfolio.interestratechart.InterestRateChartSlabApiConstants.periodTypeParamName;
import static org.mifosplatform.portfolio.interestratechart.InterestRateChartSlabApiConstants.toPeriodParamName;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.portfolio.savings.SavingsPeriodFrequencyType;

@Embeddable
public class InterestRateChartSlabFields {

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "period_type_enum", nullable = false)
    private Integer periodType;

    @Column(name = "from_period", nullable = false)
    private Integer fromPeriod;

    @Column(name = "to_period", nullable = true)
    private Integer toPeriod;

    @Column(name = "amount_range_from", scale = 6, precision = 19)
    private BigDecimal amountRangeFrom;

    @Column(name = "amount_range_to", scale = 6, precision = 19)
    private BigDecimal amountRangeTo;

    @Column(name = "annual_interest_rate", scale = 6, precision = 19, nullable = false)
    private BigDecimal annualInterestRate;

    @Column(name = "interest_rate_for_female", scale = 6, precision = 19, nullable = true)
    private BigDecimal interestRateForFemale;

    @Column(name = "interest_rate_for_children", scale = 6, precision = 19, nullable = true)
    private BigDecimal interestRateForChildren;

    @Column(name = "interest_rate_for_senior_citizen", scale = 6, precision = 19, nullable = true)
    private BigDecimal interestRateForSeniorCitizen;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    protected InterestRateChartSlabFields() {
        //
    }

    public static InterestRateChartSlabFields createNew(final String description, final SavingsPeriodFrequencyType periodFrequencyType,
            final Integer fromPeriod, final Integer toPeriod, final BigDecimal amountRangeFrom, final BigDecimal amountRangeTo,
            final BigDecimal annualInterestRate, final BigDecimal interestRateForFemale, final BigDecimal interestRateForChildren,
            final BigDecimal interestRateForSeniorCitizen, final String currencyCode) {
        return new InterestRateChartSlabFields(description, periodFrequencyType, fromPeriod, toPeriod, amountRangeFrom, amountRangeTo,
                annualInterestRate, interestRateForFemale, interestRateForChildren, interestRateForSeniorCitizen, currencyCode);
    }

    private InterestRateChartSlabFields(final String description, final SavingsPeriodFrequencyType periodFrequencyType,
            final Integer fromPeriod, final Integer toPeriod, final BigDecimal amountRangeFrom, final BigDecimal amountRangeTo,
            final BigDecimal annualInterestRate, final BigDecimal interestRateForFemale, final BigDecimal interestRateForChildren,
            final BigDecimal interestRateForSeniorCitizen, final String currencyCode) {
        this.description = description;
        this.periodType = (periodFrequencyType == null) ? null : periodFrequencyType.getValue();
        this.fromPeriod = fromPeriod;
        this.toPeriod = toPeriod;
        this.amountRangeFrom = amountRangeFrom;
        this.amountRangeTo = amountRangeTo;
        this.annualInterestRate = annualInterestRate;
        this.interestRateForFemale = interestRateForFemale;
        this.interestRateForChildren = interestRateForChildren;
        this.interestRateForSeniorCitizen = interestRateForSeniorCitizen;
        this.currencyCode = currencyCode;
    }

    public void update(final JsonCommand command, final Map<String, Object> actualChanges, final DataValidatorBuilder baseDataValidator) {

        if (command.isChangeInStringParameterNamed(descriptionParamName, this.description)) {
            final String newValue = command.stringValueOfParameterNamed(descriptionParamName);
            actualChanges.put(descriptionParamName, newValue);
            this.description = newValue;
        }

        if (command.isChangeInIntegerParameterNamed(periodTypeParamName, this.periodType)) {
            final Integer newValue = command.integerValueOfParameterNamed(periodTypeParamName);
            actualChanges.put(periodTypeParamName, newValue);
            this.periodType = newValue;
        }

        if (command.isChangeInIntegerParameterNamed(fromPeriodParamName, this.fromPeriod)) {
            final Integer newValue = command.integerValueOfParameterNamed(fromPeriodParamName);
            actualChanges.put(fromPeriodParamName, newValue);
            this.fromPeriod = newValue;
        }

        if (command.isChangeInIntegerParameterNamed(toPeriodParamName, this.toPeriod)) {
            final Integer newValue = command.integerValueOfParameterNamed(toPeriodParamName);
            actualChanges.put(toPeriodParamName, newValue);
            this.toPeriod = newValue;
        }

        if (command.isChangeInBigDecimalParameterNamed(amountRangeFromParamName, this.amountRangeFrom)) {
            final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(amountRangeFromParamName);
            actualChanges.put(amountRangeFromParamName, newValue);
            this.amountRangeFrom = newValue;
        }

        if (command.isChangeInBigDecimalParameterNamed(amountRangeToParamName, this.amountRangeTo)) {
            final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(amountRangeToParamName);
            actualChanges.put(amountRangeToParamName, newValue);
            this.amountRangeTo = newValue;
        }

        if (command.isChangeInBigDecimalParameterNamed(annualInterestRateParamName, this.annualInterestRate)) {
            final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(annualInterestRateParamName);
            actualChanges.put(annualInterestRateParamName, newValue);
            this.annualInterestRate = newValue;
        }

        if (command.isChangeInBigDecimalParameterNamed(interestRateForFemaleParamName, this.interestRateForFemale)) {
            final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(interestRateForFemaleParamName);
            actualChanges.put(interestRateForFemaleParamName, newValue);
            this.interestRateForFemale = newValue;
        }

        if (command.isChangeInBigDecimalParameterNamed(interestRateForChildrenParamName, this.interestRateForChildren)) {
            final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(interestRateForChildrenParamName);
            actualChanges.put(interestRateForChildrenParamName, newValue);
            this.interestRateForChildren = newValue;
        }

        if (command.isChangeInBigDecimalParameterNamed(interestRateForSeniorCitizenParamName, this.interestRateForSeniorCitizen)) {
            final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(interestRateForSeniorCitizenParamName);
            actualChanges.put(interestRateForSeniorCitizenParamName, newValue);
            this.interestRateForSeniorCitizen = newValue;
        }

        validateChartSlabPlatformRules(command, baseDataValidator);
    }

    public void validateChartSlabPlatformRules(final JsonCommand chartSlabsCommand, final DataValidatorBuilder baseDataValidator) {
        if (isFromPeriodGreaterThanToPeriod()) {
            final Integer fromPeriod = chartSlabsCommand.integerValueOfParameterNamed(fromPeriodParamName);
            baseDataValidator.parameter(fromPeriodParamName).value(fromPeriod).failWithCode("from.period.is.greater.than.to.period");
        }

        if (isAmountRangeFromGreaterThanTo()) {
            final BigDecimal amountRangeFrom = chartSlabsCommand.bigDecimalValueOfParameterNamed(amountRangeFromParamName);
            baseDataValidator.parameter(amountRangeFromParamName).value(amountRangeFrom)
                    .failWithCode("amount.range.from.is.greater.than.amount.range.to");
        }
    }

    public boolean isFromPeriodGreaterThanToPeriod() {
        boolean isGreater = false;
        if (this.toPeriod != null && this.fromPeriod.compareTo(this.toPeriod) > 1) {
            isGreater = true;
        }
        return isGreater;
    }

    public boolean isAmountRangeFromGreaterThanTo() {
        boolean isGreater = false;
        if (this.amountRangeFrom != null && this.amountRangeTo != null && this.amountRangeFrom.compareTo(this.amountRangeTo) > 1) {
            isGreater = true;
        }
        return isGreater;
    }

    public Integer periodType() {
        return this.periodType;
    }

    public Integer fromPeriod() {
        return this.fromPeriod;
    }

    public Integer toPeriod() {
        return this.toPeriod;
    }

    public boolean isPeriodOverlapping(final InterestRateChartSlabFields that) {
        if (that.toPeriod == null) {
            if (this.toPeriod == null) { return true; }
            return that.fromPeriod <= this.toPeriod;
        }
        return this.fromPeriod <= that.toPeriod && that.fromPeriod <= this.toPeriod;
    }

    public boolean isBetweenPeriod(final LocalDate periodStartDate, final LocalDate periodEndDate) {
        final Integer compare = depositPeriod(periodStartDate, periodEndDate);
        return (compare < this.fromPeriod || (this.toPeriod != null && compare > this.toPeriod)) ? false : true;
    }

    public boolean isAmountRangeProvided() {
        return (this.amountRangeFrom == null) ? false : true;
    }

    public BigDecimal annualInterestRate() {
        return this.annualInterestRate;
    }

    public BigDecimal interestRateForFemale() {
        return this.interestRateForFemale;
    }

    public BigDecimal interestRateForChildren() {
        return this.interestRateForChildren;
    }

    public BigDecimal interestRateForSeniorCitizen() {
        return this.interestRateForSeniorCitizen;
    }

    public Integer depositPeriod(final LocalDate periodStartDate, final LocalDate periodEndDate) {
        Integer actualDepositPeriod = 0;
        final SavingsPeriodFrequencyType periodFrequencyType = SavingsPeriodFrequencyType.fromInt(periodType());
        switch (periodFrequencyType) {
            case DAYS:
                actualDepositPeriod = Days.daysBetween(periodStartDate, periodEndDate).getDays();
            break;
            case WEEKS:
                actualDepositPeriod = Weeks.weeksBetween(periodStartDate, periodEndDate).getWeeks();
            break;
            case MONTHS:
                actualDepositPeriod = Months.monthsBetween(periodStartDate, periodEndDate).getMonths();
            break;
            case YEARS:
                actualDepositPeriod = Years.yearsBetween(periodStartDate, periodEndDate).getYears();
            break;
            case INVALID:
                actualDepositPeriod = 0;// default value
            break;
        }
        return actualDepositPeriod;
    }

    public boolean isAmountBetween(final BigDecimal depositAmount) {
        boolean returnValue = true;
        if (amountRangeFrom != null && amountRangeTo != null) {
            returnValue = depositAmount.compareTo(amountRangeFrom) >= 0 && depositAmount.compareTo(amountRangeTo) <= 0;
        } else if (amountRangeFrom != null) {
            returnValue = depositAmount.compareTo(amountRangeFrom) >= 0;
        } else if (amountRangeTo != null) {
            returnValue = depositAmount.compareTo(amountRangeTo) <= 0;
        }
        return returnValue;
    }

}