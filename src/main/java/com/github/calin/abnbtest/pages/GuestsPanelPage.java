package com.github.calin.abnbtest.pages;

import com.github.calin.abnbtest.config.FrameworkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GuestsPanelPage extends BasePage {

  @Value("${locators.GuestsPanelPage.adultsCount}")
  String adultsCountLocator;

  @Value("${locators.GuestsPanelPage.increaseAdultsCount}")
  String increaseAdultsLocator;

  @Value("${locators.GuestsPanelPage.decreaseAdultsCount}")
  String decreaseAdultsLocator;

  @Value("${locators.GuestsPanelPage.childrenCount}")
  String childrenCountLocator;

  @Value("${locators.GuestsPanelPage.increaseChildrenCount}")
  String increaseChildrenLocator;

  @Value("${locators.GuestsPanelPage.decreaseChildrenCount}")
  String decreaseChildrenLocator;

  @Value("${locators.GuestsPanelPage.infantsCount}")
  String infantsCountLocator;

  @Value("${locators.GuestsPanelPage.increaseInfantsCount}")
  String increaseInfantsLocator;

  @Value("${locators.GuestsPanelPage.decreaseInfantsCount}")
  String decreaseInfantsLocator;

  public GuestsPanelPage(FrameworkContext context) {
    super(context);
  }

  /**
   * Returns the number of adults currently selected
   *
   * @return number of adults
   */
  public int getAdultsCount() {
    return Integer.parseInt(getText(getByFor(adultsCountLocator)));
  }

  /**
   * Returns the number of children currently selected
   *
   * @return number of children
   */
  public int getChildrenCount() {
    return Integer.parseInt(getText(getByFor(childrenCountLocator)));
  }

  /**
   * Returns the number of infants currently selected
   *
   * @return number of infatns
   */
  public int getInfantsCount() {
    return Integer.parseInt(getText(getByFor(infantsCountLocator)));
  }

  /**
   * Sets the number of adults send as parameter. Will decrease or increase the selected number of
   * adults
   *
   * @param adults desired number of adults to be selected
   */
  public void setNumberOfAdults(int adults) {
    int currentAdultsCount = getAdultsCount();
    Runnable action = currentAdultsCount < adults ? this::addAdult : this::removeAdult;
    int actionCount = Math.abs(currentAdultsCount - adults);
    performActionFor(actionCount, action);
  }

  /**
   * Sets the number of children send as parameter. Will decrease or increase the selected number of
   * children
   *
   * @param children desired number of children to be selected
   */
  public void setNumberOfChildren(int children) {
    int currentChildrenCount = getChildrenCount();
    Runnable action = currentChildrenCount < children ? this::addChild : this::removeChild;
    int actionCount = Math.abs(currentChildrenCount - children);
    performActionFor(actionCount, action);
  }

  /**
   * Sets the number of infants send as parameter. Will decrease or increase the selected number of
   * infants
   *
   * @param infants desired number of infants to be selected
   */
  public void setNumberOfInfants(int infants) {
    int currentInfantsCount = getInfantsCount();
    Runnable action = currentInfantsCount < infants ? this::addInfant : this::removeInfant;
    int actionCount = Math.abs(currentInfantsCount - infants);
    performActionFor(actionCount, action);
  }

  private void addInfant() {
    click(getByFor(increaseInfantsLocator));
  }

  private void removeInfant() {
    click(getByFor(decreaseInfantsLocator));
  }

  private void addChild() {
    click(getByFor(increaseChildrenLocator));
  }

  private void removeChild() {
    click(getByFor(decreaseChildrenLocator));
  }

  private void removeAdult() {
    click(getByFor(decreaseAdultsLocator));
  }

  private void addAdult() {
    click(getByFor(increaseAdultsLocator));
  }
}
